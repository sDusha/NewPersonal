<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="container mt-4 row justify-content-between">
        <#if group??>
            <div class="col-md-5">
                <#if user.isOwner() || user.isAdmin() || user == group.owner || check>
                    <!-- полный доступ к информации о группе, также дать доступ участникам группы -->
                    <div>
                        <h2>Название: ${group.name}</h2>
                        <p>Краткое описание: ${group.shortDescription}</p>
                        <p>Владелец: <a href="/user/${group.owner.id}" class="btn btn-outline-secondary m5 btn-sm">${group.owner.name}</a></p>
                        <p>Полное описание: ${group.description}</p>
                    </div>
                    <div>
                        <h2>Лидер команды: <a href="/user/${group.owner.id}" class="btn btn-outline-secondary m5 btn-sm"> ${group.owner.name}</a></h2>
                        <#if users?size != 0>
                            <#list users as persona>
                                <p style="color: gray"><a href="/user/${persona.id}" class="btn btn-outline-secondary m5 btn-sm">${persona.name}</a>
                                Номер: ${persona.phoneNumber}</p>
                            </#list>
                        <#else >
                            <p>В команде есть только владелец</p>
                        </#if>
                    </div>
                <#else >
                    <h1>Доступ для 3-их лиц закрыт</h1>
                </#if>
                <#if user.isAdmin() || user.isOwner() || group.owner == user>
                    <div>
                        <!--
                            Сделать возможность добавлять новых пользователей в группу/ удалять старых
                            Сделать возможность менять описани??
                        -->
                        <#if personal?size != 0>
                            <form action="/group/${group.id}/addUser" method="post">
                                <div class="form-group">
                                    <label for="status"> Добавить пользователя</label>
                                    <select id="status" required="" name="status" class="form-control">
                                        <#list personal as persona>
                                            <option value="${persona.id}">${persona.name}, ${persona.phoneNumber}</option>
                                        </#list>
                                    </select>
                                </div>
                                <input type="hidden" value="${user.id}" name="personaId">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <button type="submit">Добавить в группу</button>
                            </form>
                        <#else >
                            <p>Нельзя никого добавить, так как все пользователи уже состоят в группе</p>
                        </#if>
                        <!-- Сделать форму удаления пользователя из группы
        <#if users?size != 0>


            <form action="/group/${group.id}/deleteUser" method="post">
                <label for="statusDelete"></label>
                <select id="statusDelete" required="" name="statusDelete" class="form-control">
                    <#list users as persona>
                        <option value="${persona.id}">${persona.name}, +${persona.phoneNumber}</option>
                    </#list>
                </select>

                <input type="hidden" value="${user.id}" name="personaId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit">Delete</button>
            </form>
            <#else >
            <p>Нельзя никого удалить, так как нет пользователей кроме владельца</p>
        </#if>
        -->
                    </div>
                </#if>
            </div>
            <div class="col-md-5">
                <#if user.isAdmin() || user.isOwner() || group.owner == user>
                    <!-- Добавление новой задачи -->
                    <div align="center">
                        <p><a href="/task/${group.id}/create" class="btn btn-outline-secondary m5 btn-sm">
                                Создать новую задачу</a></p>
                    </div>
                </#if>
                <#if tasks?size != 0>
                    <#list tasks as task>
                        <div class="card shadow-sm card-body mb-4">
                            <p class="card-text">${task.name} <small>${task.shortDescription}</small></p>
                            <a href="/task/${task.id}" class="btn btn-outline-secondary m5 btn-sm">
                                подробнее
                            </a>
                            <!--<p>Статус: ${task.status.getRussianTranslation()}</p>-->

                            <#if user.isOwner() || user.isAdmin() || user = group.owner>
                                <p><#if task.status.name() != "START">
                                        <a href="/task/${task.id}/status/down" class="btn btn-outline-secondary m5 btn-sm">
                                            перейти на прошлый этап
                                        </a>
                                    </#if>
                                    ${task.status.getRussianTranslation()}
                                    <#if task.status.name() != "READY">
                                        <a href="/task/${task.id}/status/up" class="btn btn-outline-secondary m5 btn-sm">
                                            перейти на следующий этап
                                        </a>
                                    </#if></p>
                            <#else>
                                <p>${task.status.getRussianTranslation()}</p>
                            </#if>


                        </div>
                    </#list>
                <#else >
                    <p>У команды отсутсвуют какие либо задачи</p>
                </#if>
            </div>
        </#if>
    </div>
</@t.template>
