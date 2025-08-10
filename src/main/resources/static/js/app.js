document.addEventListener('DOMContentLoaded', function() {
    loadMovies();
    loadTotals();

    const modal = document.getElementById('modal');
    const openModalBtn = document.getElementById('open-modal');
    const closeModalBtn = document.getElementById('close-modal');
    const movieForm = document.getElementById('movie-form');

    openModalBtn.addEventListener('click', () => {
        modal.style.display = 'block';
        document.body.style.overflow = 'hidden';
    });

    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
        document.body.style.overflow = 'auto';
    });

    window.addEventListener('click', (e) => {
        if (e.target === modal) {
            modal.style.display = 'none';
            document.body.style.overflow = 'auto';
        }
    });

    movieForm.addEventListener('submit', function(e) {
        e.preventDefault();
        addMovie();
    });
});

async function loadMovies() {
    try {
        const response = await fetch('/api/movies');
        if (!response.ok) throw new Error('Failed to load movies');

        const movies = await response.json();
        const movieList = document.getElementById('movie-list');
        movieList.innerHTML = '';

        if (movies.length === 0) {
            movieList.innerHTML = '<p class="no-movies">No movies available. Add the first one!</p>';
            return;
        }

        movies.forEach(movie => {
            const movieElement = createMovieElement(movie);
            movieList.appendChild(movieElement);
        });
    } catch (error) {
        console.error('Error:', error);
        showAlert('error', 'Failed to load movies. Please refresh the page.');
    }
}

function createMovieElement(movie) {
    const movieElement = document.createElement('div');
    movieElement.className = 'movie';
    movieElement.dataset.id = movie.id;

    movieElement.innerHTML = `
        <div class="movie-image-container">
            <img src="${movie.imageUrl || 'https://via.placeholder.com/120x160?text=No+Image'}" 
                 alt="${movie.title}" 
                 class="movie-image"
                 onerror="this.src='https://via.placeholder.com/120x160?text=Image+Not+Available'">
        </div>
        <div class="movie-details">
            <h3>${movie.title}</h3>
            <p><strong>Genre:</strong> ${movie.genre}</p>
            ${movie.description ? `<p class="movie-description">${movie.description}</p>` : ''}
            <div class="votes">
                <button class="vote-btn like-btn" onclick="vote(${movie.id}, 'like')">
                    <i class="fas fa-thumbs-up"></i> <span class="vote-count">${movie.likes}</span>
                </button>
                <button class="vote-btn dislike-btn" onclick="vote(${movie.id}, 'dislike')">
                    <i class="fas fa-thumbs-down"></i> <span class="vote-count">${movie.dislikes}</span>
                </button>
            </div>
        </div>
    `;

    return movieElement;
}

async function loadTotals() {
    try {
        const response = await fetch('/api/movies/totals');
        if (!response.ok) throw new Error('Failed to load totals');

        const totals = await response.json();
        document.getElementById('total-likes').textContent = totals.totalLikes;
        document.getElementById('total-dislikes').textContent = totals.totalDislikes;
    } catch (error) {
        console.error('Error:', error);
    }
}

async function vote(movieId, type) {
    try {
        const response = await fetch(`/api/movies/${movieId}/${type}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) throw new Error('Failed to register vote');

        const updatedMovie = await response.json();
        updateMovieInUI(updatedMovie);
        loadTotals();

        const button = document.querySelector(`.movie[data-id="${movieId}"] .${type}-btn`);
        button.classList.add('pulse');
        setTimeout(() => button.classList.remove('pulse'), 300);

    } catch (error) {
        console.error('Error:', error);
        showAlert('error', 'Failed to register vote. Please try again.');
    }
}

function updateMovieInUI(movie) {
    const movieElement = document.querySelector(`.movie[data-id="${movie.id}"]`);
    if (movieElement) {
        const likeCount = movieElement.querySelector('.like-btn .vote-count');
        const dislikeCount = movieElement.querySelector('.dislike-btn .vote-count');

        likeCount.textContent = movie.likes;
        dislikeCount.textContent = movie.dislikes;
    }
}

async function addMovie() {
    const title = document.getElementById('title').value.trim();
    const genre = document.getElementById('genre').value.trim();
    const imageUrl = document.getElementById('imageUrl').value.trim();
    const description = document.getElementById('description').value.trim();

    if (!title || !genre || !imageUrl) {
        showAlert('error', 'Please fill all required fields!');
        return;
    }

    try {
        const response = await fetch('/api/movies', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                title,
                genre,
                imageUrl,
                description: description || null
            })
        });

        if (!response.ok) throw new Error('Failed to add movie');

        const newMovie = await response.json();

        const movieList = document.getElementById('movie-list');
        if (movieList.querySelector('.no-movies')) {
            movieList.innerHTML = '';
        }

        const movieElement = createMovieElement(newMovie);
        movieList.prepend(movieElement);

        document.getElementById('movie-form').reset();
        document.getElementById('modal').style.display = 'none';
        document.body.style.overflow = 'auto';

        showAlert('success', 'Movie added successfully!');

    } catch (error) {
        console.error('Error:', error);
        showAlert('error', 'Failed to add movie. Please check the data and try again.');
    }
}

function showAlert(type, message) {
    const alertBox = document.createElement('div');
    alertBox.className = `alert alert-${type}`;
    alertBox.textContent = message;

    document.body.appendChild(alertBox);

    setTimeout(() => {
        alertBox.classList.add('fade-out');
        setTimeout(() => alertBox.remove(), 500);
    }, 3000);
}