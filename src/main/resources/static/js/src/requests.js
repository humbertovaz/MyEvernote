

$('.card-text').onChange(function(){
    const url='/editNotes/';
    const id = $(this).attr('id')
    const myDescription = $(id).text()
    const data = {description: myDescription};
    $.ajax({
        type: "PUT",
        url: url + id,
        data: data,
        contentType : 'application/json',
        success: console.log("success")
    });
})
