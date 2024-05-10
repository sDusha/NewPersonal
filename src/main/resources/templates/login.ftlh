<#import "blocks/template.ftlh" as t>
<@t.template user>
    <main class="form-signin w-50 m-auto">
        <h4>Авторизация</h4>
        <form action="/login" method="post">
            <h1 class="h3 mb-3 fw-normal">Добро пожаловать!</h1>

            <div class="form-floating">
                <input type="email" name="username" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Email address</label>
            </div>
            <br>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input class="btn btn-primary w-100 py-2" type="submit" value="Войти">
        </form>
    </main>
</@t.template>