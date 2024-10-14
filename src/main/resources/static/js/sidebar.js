document.addEventListener('DOMContentLoaded', function() {
    const compassButton = document.querySelector('.navi-item a i.far.fa-compass').closest('a');
    const sidebar = document.getElementById('sidebar');
    const closeSidebarButton = document.getElementById('closeSidebar');
    const searchInput = document.getElementById('searchInput');
    const searchButton = document.getElementById('searchButton');
    const searchResults = document.getElementById('searchResults');

    compassButton.addEventListener('click', function(e) {
        e.preventDefault();
        sidebar.classList.add('active');
    });

    closeSidebarButton.addEventListener('click', function() {
        sidebar.classList.remove('active');
    });

    searchButton.addEventListener('click', performSearch);
    searchInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            performSearch();
        }
    });

    function performSearch() {
        const query = searchInput.value.trim();
        if (query) {
            fetch(`/api/search?query=${encodeURIComponent(query)}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(results => {
                    displayResults(results);
                })
                .catch(error => {
                    console.error('Error:', error);
                    searchResults.innerHTML = '<li class="error-message">검색 중 오류가 발생했습니다.</li>';
                });
        } else {
            searchResults.innerHTML = '';
        }
    }

    function displayResults(results) {
        searchResults.innerHTML = '';
        if (results.length === 0) {
            searchResults.innerHTML = '<li class="no-results">검색 결과가 없습니다.</li>';
        } else {
            results.forEach(user => {
                const li = document.createElement('li');
                li.className = 'search-result-item';
                li.innerHTML = `
                    <a href="/user/${user.id}" class="user-link">
                        <span class="username">@${user.username}</span>
                        <span class="nickname">${user.nickname}</span>
                    </a>
                `;
                li.querySelector('.user-link').addEventListener('click', function(e) {
                    e.preventDefault();
                    window.location.href = this.getAttribute('href');
                    sidebar.classList.remove('active');
                });
                searchResults.appendChild(li);
            });
        }
    }
});