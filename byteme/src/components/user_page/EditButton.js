function clickEditBtn(event) {
    editField(event);
}

function editField(event) {
    const currentContainer = event.target.parentNode.parentNode.firstChild;
    const currentTextContent = currentContainer.textContent;

    const input = document.createElement('input');
    const saveButton = document.createElement('button');
    saveButton.textContent = "Save";
    input.value = currentTextContent;
    input.type = 'text';
    currentContainer.innerHTML = "";
    currentContainer.appendChild(input);
    currentContainer.appendChild(saveButton);
    input.focus();

    saveButton.addEventListener('click', function (e) {
        const savedTextContent = input.value;
        currentContainer.innerHTML = "";
        currentContainer.textContent = savedTextContent;
    });
}

const EditButton = () => {
    return (
        <div className="edit">
            <button className="edit-btn" onClick={clickEditBtn}>Edit</button>
        </div>
    );
};

export default EditButton;