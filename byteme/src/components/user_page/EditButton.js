function clickEditBtn(event) {
    editField(event);
}

function editField(event) {
    // const currentContainer = event.target.parentNode.parentNode.childNodes[1];
    const currentContainer = document.querySelector(".user-data-" + event.target.dataset.container)
    const currentTextContent = currentContainer.textContent;

    const editBtnContainer = event.target.parentNode;
    const editButton = event.target;
    const input = document.createElement('input');
    const saveButton = document.createElement('button');
    saveButton.classList.add("edit-btn");
    saveButton.classList.add("button");
    saveButton.classList.add("button-light");
    saveButton.textContent = "Save";
    input.classList.add("form-control");
    input.value = currentTextContent;
    input.type = 'text';
    currentContainer.innerHTML = "";
    currentContainer.appendChild(input);
    // currentContainer.appendChild(saveButton);
    editBtnContainer.appendChild(saveButton);
    editBtnContainer.removeChild(editButton);
    input.focus();

    saveButton.addEventListener('click', function (e) {
        const savedTextContent = input.value;
        currentContainer.innerHTML = "";
        currentContainer.textContent = savedTextContent;
        editBtnContainer.removeChild(saveButton);
        editBtnContainer.appendChild(editButton);
    });
}

const EditButton = ({dataset}) => {
    return (
        <div className="edit">
            <button className="edit-btn button button-light" data-container={dataset} onClick={clickEditBtn}>Edit
            </button>
        </div>
    );
};

export default EditButton;