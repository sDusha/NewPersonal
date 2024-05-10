<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="row g-5">
        <#if ownerTeams?size == 0 && userTeams?size == 0>
            <h1>Доступных команд нет</h1>
        <#else >
            <h4>Список доступных команд</h4>
        </#if>
        <#if teams??>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <#if user.isOwner() || user.isAdmin()>
                    <#list teams as team>
                        <div class="col">
                            <div class="card shadow-sm card-body">
                                <p class="card-text">
                                    <b>${team.name}</b> <br>
                                    <i>${team.shortDescription}</i>

                                    <a href="/group/${team.id}" class="btn btn-outline-secondary m5 btn-sm">Подробная информация</a></p>
                            </div>
                        </div>
                    </#list>
                <#else >
                    <#list userTeams as team>
                        <div class="col">
                            <div class="card shadow-sm card-body">
                                <p class="card-text">
                                    <b>${team.name}</b> <br>
                                    <i>${team.shortDescription}</i>

                                    <a href="/group/${team.id}" class="btn btn-outline-secondary m5 btn-sm">Подробная информация</a></p>
                            </div>
                        </div>
                    </#list>
                    <#list ownerTeams as team>
                        <div class="col">
                            <div class="card shadow-sm card-body">
                                <p class="card-text">
                                    <b>${team.name}</b> <br>
                                    <i>${team.shortDescription}</i>

                                    <a href="/group/${team.id}" class="btn btn-outline-secondary m5 btn-sm">Подробная информация</a></p>
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
            <#else>
            <h1>Команд не существует</h1>
        </#if>
    </div>
</@t.template>