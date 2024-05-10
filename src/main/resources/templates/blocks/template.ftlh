<#macro template user>
    <!DOCTYPE html>
    <html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Персонал</title>
    </head>
    <body>
    <!-- Bootstrap header -->
    <div class="container">
        <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <#if user.email??>
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><form action="/logout" method="post" >
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input class="btn btn-outline-secondary m5 btn-sm" type="submit" value="Выйти">
                        </form></li>
                </ul>
                <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0 nav-pills">
                    <li><a href="/groups" class="btn btn-outline-secondary m5 btn-sm">Команды</a></li>
                    <#if user.isAdmin() || user.isOwner()>
                        <li><a href="/group/create" class="btn btn-outline-secondary m5 btn-sm">Создать команду</a></li>
                    </#if>
                    <#if user.isOwner()>
                        <li><a href="/admin" class="btn btn-outline-secondary m5 btn-sm">Админ панель</a></li>
                    </#if>
                    <li><a href="/profile" class="btn btn-outline-secondary m5 btn-sm">Профиль</a></li>
                </ul>
            <#else>
                <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0 nav-pills">
                    <li><a href="/login" class="nav-link active m5 btn-sm">Вход</a></li>
                    <li><a href="/registration" class="nav-link active m5 btn-sm">Регистрация</a></li>

                </ul>
            </#if>
        </header>
    </div>
    <div class="container mt-4">
        <#nested/>
    </div>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <!-- Скрипт для сохранения позиции -->
    <script type="application/javascript">
        window.onload = function() {
            var scrollPosition = sessionStorage.getItem('scrollPosition');
            window.scrollTo(0, scrollPosition || 0);
        }

        // Сохраняем позицию прокрутки при обновлении страницы
        window.onbeforeunload = function() {
            sessionStorage.setItem('scrollPosition', window.scrollY);
        }
    </script>
    </body>
    </html>
</#macro>
