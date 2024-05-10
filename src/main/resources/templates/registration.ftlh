<#import "blocks/template.ftlh" as t>
<@t.template user>
    <main class="form-signin w-50 m-auto">
        <h4>Регистрация</h4>
        <form action="/registration" method="post">
            <div class="form-floating">
                <input type="text" name="name" class="form-control" id="name" placeholder="name">
                <label for="name">Имя пользователя:</label>
            </div>
            <br>
            <div class="form-floating">
                <input type="email" name="email" class="form-control" id="email" placeholder="name@example.com">
                <label for="email">Эл. почта:</label>
            </div>
            <br>
            <div class="form-floating">
                <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" placeholder="88005553535">
                <label for="phoneNumber">Номер телефона:</label>
            </div>
            <br>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" class="btn btn-primary w-100 py-2" value="Зарегестрироваться "/>
        </form>
    </main>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage} </h2>
    </#if>
</@t.template>