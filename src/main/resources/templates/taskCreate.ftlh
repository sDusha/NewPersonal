<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div class="col-md-7 col-lg-8">
        <div class="mb-3 collapse show" id="collapseExample" style="">
            <form action="/task/${group.id}/create" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">Название задачи</label>
                    <input type="text" required="" class="form-control" id="name" name="name">
                </div><br>

                <div class="form-group">
                    <label for="shortDescription">Краткая заметка о задаче</label>
                    <input type="text" required="" class="form-control" id="shortDescription" name="shortDescription">
                </div><br>

                <div class="form-group">
                    <label for="description">Полное описание</label>
                    <input type="text" required="" class="form-control" id="description" name="description">
                </div><br>

                <div class="form-group">
                    <label for="deadline">Срок сдачи</label>
                    <input type="datetime-local" required="" class="form-control" id="deadline" name="deadline">
                </div><br>

                <div class="form-group">
                    <label for="level">Урвень сложности</label>
                    <input type="text" required="" class="form-control" id="level" name="level">
                </div><br>

                <input type="hidden" value="${group.id}" name="team">
                <input type="hidden" name="_csrf" value="${_csrf.token}">

                <button type="submit" style="width: 100%" class="btn btn-outline-secondary m5 btn-sm">Добавить</button>
            </form>
        </div>
    </div>

</@t.template>