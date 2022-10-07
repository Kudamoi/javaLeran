document.addEventListener("DOMContentLoaded", function() {

  let form = $(".main__form");
  let list = $(".main__list");

//  $.ajax({
//    url: "/list/",
//    type: "GET",
//    dataType: "json",
//    success: function (response) {
//      for (let i = 0; i < response.length; i++) {
//        createCase(response[i]);
//      }
//    },
//    error: function (response) {
//      console.log("ERROR");
//      console.log(response);
//    },
//  })

  form.on("submit", (e) => {
    e.preventDefault();
    let field = form.find(".main__field");

    if (field.val().length > 0) {
      $.ajax({
        url: "/list/",
        type: "PUT",
        dataType: "json",
        data: {"name": field.val()},
        success: function (response) {
          createCase(response);
        },
        error: function (response) {
          console.log("ERROR");
          console.log(response);
        },
      })
    } else {
      alert("Название не может быть пустым");
    }
  });

list.find(".main__item").each(function() {
addEvent($(this).find("button")[0])
});

  function createCase(response) {
    let cas = document.createElement("li");
    let content = document.createElement("span");
    let button = document.createElement("button");
    cas.classList.add("main__item");

    button.dataset.id = response.id;
    button.textContent = "Delete case";
    content.textContent = response.name;

    addEvent(button);

    cas.append(content);
    cas.append(button);
    list.append(cas);
  }

  function addEvent(block) {
    block.addEventListener("click", (e) => {
      e.preventDefault();

      $.ajax({
        url: "/list/" + block.dataset.id,
        type: "DELETE",
        dataType: "json",
        success: function (response) {
          block.parentNode.remove();
        },
        error: function (response) {
          console.log("ERROR");
          console.log(response);
        },
      })
    });
  }
});
