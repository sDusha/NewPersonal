// Сохраняем текущую позицию прокрутки при загрузке страницы
window.onload = function() {
    var scrollPosition = sessionStorage.getItem('scrollPosition');
    window.scrollTo(0, scrollPosition || 0);
}

// Сохраняем позицию прокрутки при обновлении страницы
window.onbeforeunload = function() {
    sessionStorage.setItem('scrollPosition', window.scrollY);
}

