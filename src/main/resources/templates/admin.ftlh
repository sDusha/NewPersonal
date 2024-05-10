<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h4>Панель администратора</h4>
    <table border="1">
        <tr>
            <th>Email</th>
            <th>Номер телефона</th>
            <th>Активность</th>
            <th>Роли</th>
            <th>Бан</th>
            <th>Статус пользователя</th>
            <th>Страница пользователя</th>
        </tr>
        <#list users as user>
            <!--" < #sep >,  " разделитель-->
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <th><#list user.roles as role>${role.getRussianTranslation()}<#sep>, </#list></th>
                <th>
                    <form action="admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="БАН">
                    </form></th>
                <th><form action="/admin/user/edit" method="post">
                        <div class="form-group">
                            <!--<label for="status"></label> -->
                            <select id="status" required="" name="status" class="form-control">
                                <#list roles as role>
                                    <option value="${role}">${role.getRussianTranslation()}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" value="${user.id}" name="personaId">
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                        <button type="submit">изменить</button>
                    </form>
                </th>
                <!--<th><a href="admin/user/edit/${user.id}">Редактирование</a></th>-->
                <th><a href="/user/${user.id}" class="btn">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Пользователей нет</h3>
        </#list>
    </table>
</@t.template>
