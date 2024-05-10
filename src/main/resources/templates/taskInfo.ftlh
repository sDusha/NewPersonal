<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#if task??>
        <#if user.isOwner() || user.isAdmin() || user == group.owner || check>
            <p>Задача: ${task.name}</p>
            <#if user.isOwner() || user.isAdmin() || user = group.owner>
                <div>
                    <#if task.status.name() != "START">
                        <a href="/task/${task.id}/status/down" class="btn btn-outline-secondary m5 btn-sm">
                            перейти на прошлый этап
                        </a>
                    </#if>
                    <p>Статус: ${task.status.getRussianTranslation()}</p>
                    <#if task.status.name() != "READY">
                        <a href="/task/${task.id}/status/up" class="btn btn-outline-secondary m5 btn-sm">
                            перейти на следующий этап
                        </a>
                    </#if>
                </div>
            <#else >
                <p>Статус: ${task.status.getRussianTranslation()}</p>
            </#if>
            <p>Краткое описание задачи: ${task.shortDescription}</p>
            <p>Главный по группе, которой принадлежит данная задача:
                <a href="/user/${group.owner.id}" class="btn btn-outline-secondary m5 btn-sm">
                    ${group.owner.name}
                </a></p>
            <p>Группа, ответственная за задачу:
                <a href="/group/${group.id}" class="btn btn-outline-secondary m5 btn-sm">
                    ${group.name}
                </a></p></p>
            <p>Дата создания задачи: ${task.dateOfCreate}</p>
            <p>Сроки сдачи задачи задачи: ${task.deadline}</p>
            <p>полное описание задачи: ${task.description}</p>
            <#else >
                <p>У вас нет доступа к информации о данной задаче</p>
        </#if>
        <#else >
        <p>Задачи нет</p>
    </#if>
</@t.template>