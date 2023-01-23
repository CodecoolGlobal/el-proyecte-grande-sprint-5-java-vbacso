const EditButtonOnModal = ({dataset}) => {
    const saveChangesBtn = document.querySelector("#save-changes-btn")
    const closeModalBtn = document.querySelector("#close-modal-btn")

    const onClickEditButton = (event) => {
        const currentContainer = document.querySelector(".user-data-" + event.target.dataset.container)
        const currentTextContent = currentContainer.textContent;
        const editBtnContainer = event.target.parentNode;
        const editButton = event.target;

        saveChangesBtn.disabled = true;
        closeModalBtn.disabled = true;

        // create input field
        const input = document.createElement('input');
        input.type = editButton.dataset.container === "name" ? 'text' : 'number';
        input.onkeydown = editButton.dataset.container === "name" ? alphaOnly : '';
        addToClassList(input, "form-control")
        input.value = currentTextContent;
        input.focus();

        // change Edit button to Save button
        const saveButton = document.createElement('button');
        saveButton.textContent = "Save";
        addToClassList(saveButton, "edit-btn", "button", "button-light");
        currentContainer.innerHTML = "";
        currentContainer.appendChild(input);
        editBtnContainer.appendChild(saveButton);
        editBtnContainer.removeChild(editButton);

        // change input field back to 'p' after save button clicked containing the edited content
        saveButton.addEventListener('click', () => {
            const savedTextContent = input.value;
            currentContainer.innerHTML = "";
            currentContainer.textContent = savedTextContent;
            editBtnContainer.removeChild(saveButton);
            editBtnContainer.appendChild(editButton);
            saveChangesBtn.disabled = false;
            closeModalBtn.disabled = false;
        });
    }

    const alphaOnly = (event) => {
        let key = event.key;
        return ((isNaN(key) || key === null || key === ' '));
    }

    const addToClassList = (element, ...classes) => {
        classes.forEach((className) => {
            element.classList.add(className);
        })
    }

    return (
        <div className="edit">
            <button className="edit-btn button button-light" data-container={dataset} onClick={onClickEditButton}>Edit
            </button>
        </div>
    );
};

export default EditButtonOnModal;
