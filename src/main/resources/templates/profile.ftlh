<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="row mt-3 mb-3">
        <div class="col-sm">
            <#if teams?size == 0>
                <h1>Пользователь не состоит не в каких группах</h1>
            <#else >
            <h1>Пользователь участник групп:</h1>
            <ul class="list-group mt-3 text-white profile_spisok">
            <#list teams as team>
                <li class="list-group-item align-content-center mb-1">

                    <a href="/group/${team.id}" style="text-decoration: none; outline: none; color: black">
                        <div class="ml-2">
                            <h6 class="mb-0">${team.name}</h6>
                            <div class="about">
                                <span>${team.shortDescription}</span>
                            </div>
                        </div>
                        </a>
                </li>
                </#list>
            </ul>
            </#if>
        </div>
        <div class="container col-sm justify-content-center">
            <div class="card_p p-3 py-4">
                <div class="text-center">
                    <!--<img src="https://i.imgur.com/stD0Q19.jpg" width="100" class="rounded-circle">-->
                    <h3 class="mt-2">${user.name}</h3>

                    <span class="mt-1 clearfix">${user.description}</span>

                    <div class="row mt-3 mb-3">

                        <div class="col-md-6">
                            <h5>Участник руппп</h5>
                            <span class="num_p">${teams?size}</span>
                        </div>
                        <div class="col-md-6">
                            <h5>Главный групп</h5>
                            <span class="num_p">${ownerTeams?size}</span>
                        </div>

                    </div>

                    <hr class="line_p">

                    <p>Номер телефона: ${user.phoneNumber}</p>
                    <p>Мыло: ${user.email}</p>
                    <#list user.roles as role>
                        <p>Роль: ${role.getRussianTranslation()}</p>
                    </#list>

                    <!--
                    <small class="mt-4">I am an android developer working at google Inc at california,USA</small>
                    -->
                </div>
            </div>
        </div>
        <div class="col-sm">
            <#if ownerTeams?size == 0>
                <h1>Пользователь не состоит не в каких группах</h1>
            <#else >
                <h1>Пользователь участник групп:</h1>
                <ul class="list-group mt-3 text-white profile_spisok">
                    <#list ownerTeams as team>
                        <li class="list-group-item align-content-center mb-1">

                            <a href="/group/${team.id}" style="text-decoration: none; outline: none; color: black">
                                    <div class="ml-2">
                                        <h6 class="mb-0">${team.name}</h6>
                                        <div class="about">
                                            <span>${team.shortDescription}</span>
                                        </div>
                                    </div>
                                </a>
                        </li>
                    </#list>
                </ul>
            </#if>
        </div>
    </div>
</@t.template>