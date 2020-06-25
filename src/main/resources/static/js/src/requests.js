$(function () {

    $('.card-text').on('change',function () {
        const url = '/editNote/';
        const select = $(this);
        const id = select.attr('id');
        const myDescription = $('#'+id).val();
        const data =  myDescription;
        $.ajax({
            type: "PATCH",
            url: url+id,
            data: data,
            contentType: 'application/json',
            success: console.log("success")
        });
    })
});
