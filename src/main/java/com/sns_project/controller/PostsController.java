package com.sns_project.controller;

import com.sns_project.config.auth.PrincipalDetails;
import com.sns_project.domain.Posts;
import com.sns_project.domain.User;
import com.sns_project.dto.PostsDto;
import com.sns_project.handler.CustomValidationException;
import com.sns_project.repository.PostsRepository;
import com.sns_project.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    // 나중에 서비스 단으로 빼자.
    @PostConstruct
    public void synchronizeSequences() {
        postsRepository.synchronizeSequence();
    }

    @GetMapping({"/posting", "/"})
    public String getPosting(@AuthenticationPrincipal UserDetails userDetails, Model model, Pageable pageable) {

        //  posting 을 가져오는 부분
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userRepository.findByUsername(username);
            Long principalId = user.getUserId();

            Page<Posts> postsPage = postsRepository.rPosts(principalId, pageable);

            List<Map<String, Object>> postsWithAuthorFlag = postsPage.getContent().stream()
                    .map(post -> {
                        Map<String, Object> postMap = new HashMap<>();
                        postMap.put("post", post);
                        postMap.put("isAuthor", post.getUser().getUserId().equals(principalId));
                        return postMap;
                    })
                    .collect(Collectors.toList());

            Page<Map<String, Object>> postsPageWithAuthorFlag = new PageImpl<>(postsWithAuthorFlag, pageable, postsPage.getTotalElements());

            model.addAttribute("posts", postsPageWithAuthorFlag);
            model.addAttribute("principal", userDetails);
        }

        // principal과 게시글 Author가 같다면 ... 버튼 활성화.

        return "image/story";

    }

    // 참고한 코드는 그냥  url 요청하면 자동으로 userDetail이 넘어가던데,,,, 참고한 코드 작동원리를 다시 살펴보기/

    @GetMapping("/posting/upload")
    public String upload(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("principal", userDetails);
        return "image/upload";
    }

    @PostMapping("/posting")
    public String uploadPosting(PostsDto postsDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 깍둑이
        if(postsDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }
        //application.yml 파일 사용 시 사용. file 경로를 지정하면,,,  난 일단 그냥 String으로 경로를 고정시켜둠.
        /*@Value("${file.path}")
        private String uploadFolder;*/
        String uploadFolder = "C:/sns_project/src/main/resources/static/upload/";

        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid+"_"+postsDto.getFile().getOriginalFilename(); // 1.jpg
        //System.out.println("이미지 파일이름 : "+imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, postsDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // image 테이블에 저장
        Posts  posts = postsDto.toEntity(imageFileName, principalDetails.getUser()); // 5cf6237d-c404-43e5-836b-e55413ed0e49_bag.jpeg
        postsRepository.save(posts);
        return "redirect:/";
    }


    @GetMapping("/posting/{postingId}/delete")
    public String deletePosting(@PathVariable Long postingId, RedirectAttributes rttr) {

        Posts posts = postsRepository.findById(postingId).orElse(null);

        if (posts != null) {
            postsRepository.deleteById(postingId);
            //리다이렉트시 한번만 등록하는 메서드
            rttr.addFlashAttribute("message", "Delete Success!!");
        }

        return "redirect:/posting";
    }

    @GetMapping("/posting/{postingId}/update")
    public String updatePosting(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long postingId, Model model) {

        model.addAttribute("principal", userDetails);
        Posts posts = postsRepository.findById(postingId).orElse(null);

        model.addAttribute("posts", posts);

        return "image/update";
    }

    /*    @GetMapping("/posting")
    public String getPosting(int principalId, Pageable pageable) {
        // api는 데이터를 리턴하는 서버!!

        Long longPrincipalId = Long.valueOf(principalId);

        Page<Posts> images = postsRepository.rPosts(longPrincipalId, pageable);

        //return "image/popular";
        return "auth/signin";
    }*/

   /* @GetMapping("/posting")
    @ResponseBody
    public String getPosting(@AuthenticationPrincipal UserDetails userDetails, Pageable pageable) {

        String username = userDetails.getUsername();

        Long principalId = userRepository.findByUsername(username).getUserId();

        Page<Posts> posts = postsRepository.rPosts(principalId, pageable);

        StringBuilder response = new StringBuilder();
        response.append("<html><body>");
        response.append("<h1>Posts</h1>");

        for (Posts post : posts.getContent()) {
            response.append("<div>");
            response.append("<h2>Post ID: ").append(post.getPostingId()).append("</h2>");
            response.append("<p>Content: ").append(post.getContent()).append("</p>");
            response.append("<p>Date: ").append(post.getDateTime()).append("</p>");
            response.append("<p>Image URL: ").append(post.getPostImageUrl()).append("</p>");
            response.append("<p>Sharing Scope: ").append(post.getSharingScope()).append("</p>");
            response.append("<p>Author ID: ").append(post.getUserId()).append("</p>");
            response.append("</div><hr>");
        }

        // Pagination information
        response.append("<p>Page ").append(posts.getNumber() + 1)
                .append(" of ").append(posts.getTotalPages()).append("</p>");

        response.append("</body></html>");

        return response.toString();
    }*/


  /*  @GetMapping(value = {"", "/"})
    public String showPosts(Model model) {
        // 로그인 확인

        // 로그인 유저 id 가져오기

        // 친구들의 posting 가져오기
            // 로그인 유저의 친구 리스트 가져오기
            // 친구들의 포스팅 리스트 가져오기
            //

    }*/

}
