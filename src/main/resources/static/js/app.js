async function voteEntry(entryId, voteType) {
    const response = await fetch(`/api/votes/entry/${entryId}?voteType=${voteType}`, {
        method: 'POST'
    });
    if (response.ok) {
        updateVoteUI('entry', entryId, voteType);
    }
}

async function favoriteEntry(entryId) {
    const response = await fetch(`/api/favorites/entry/${entryId}`, {
        method: 'POST'
    });
    if (response.ok) {
        updateFavoriteUI('entry', entryId);
    }
}

async function voteComment(commentId, voteType) {
    const response = await fetch(`/api/votes/entry-comment/${commentId}?voteType=${voteType}`, {
        method: 'POST'
    });
    if (response.ok) {
        updateVoteUI('comment', commentId, voteType);
    }
}

async function favoriteComment(commentId) {
    const response = await fetch(`/api/favorites/entry-comment/${commentId}`, {
        method: 'POST'
    });
    if (response.ok) {
        updateFavoriteUI('comment', commentId);
    }
}

function updateVoteUI(type, id, voteType) {
    const upBtn = document.getElementById(`${type}-upvote-${id}`);
    const downBtn = document.getElementById(`${type}-downvote-${id}`);

    if (voteType === 'UPVOTE') {
        if (upBtn.classList.contains('text-success')) {
            upBtn.classList.replace('text-success', 'text-secondary');
        } else {
            upBtn.classList.replace('text-secondary', 'text-success');
            downBtn.classList.replace('text-danger', 'text-secondary');
        }
    } else {
        if (downBtn.classList.contains('text-danger')) {
            downBtn.classList.replace('text-danger', 'text-secondary');
        } else {
            downBtn.classList.replace('text-secondary', 'text-danger');
            upBtn.classList.replace('text-success', 'text-secondary');
        }
    }
}

function updateFavoriteUI(type, id) {
    const favBtn = document.getElementById(`${type}-fav-${id}`);
    const favCount = document.getElementById(`${type}-fav-count-${id}`);
    let count = parseInt(favCount.innerText);

    if (favBtn.classList.contains('entry-faved')) {
        favBtn.classList.replace('entry-faved', 'entry-not-faved');
        favCount.innerText = count - 1;
    } else {
        favBtn.classList.replace('entry-not-faved', 'entry-faved');
        favCount.innerText = count + 1;
    }
}

// Autocomplete Search
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const resultsContainer = document.getElementById('autocomplete-results');

    if (searchInput) {
        searchInput.addEventListener('input', async function() {
            const term = this.value;
            if (term.length < 2) {
                resultsContainer.style.display = 'none';
                return;
            }

            const response = await fetch(`/api/topics/search?term=${term}`);
            if (response.ok) {
                const results = await response.json();
                if (results.length > 0) {
                    resultsContainer.innerHTML = results.map(topic => 
                        `<a href="/search?term=${topic}" class="list-group-item list-group-item-action bg-dark text-white border-secondary">${topic}</a>`
                    ).join('');
                    resultsContainer.style.display = 'block';
                } else {
                    resultsContainer.style.display = 'none';
                }
            }
        });

        document.addEventListener('click', function(e) {
            if (e.target !== searchInput) {
                resultsContainer.style.display = 'none';
            }
        });
    }
});
