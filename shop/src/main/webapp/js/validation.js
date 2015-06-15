function check(formID) {
    var pattern = null;
        
    switch(formID){
        case 'name':
            pattern =  new RegExp('(^[A-Z]?[a-z]{1,15}$)|(^[А-Я]?[а-я]{1,15}$)', '');
            break;
                
        case 'surname':
            pattern =  new RegExp('(^[A-Z]?[a-z]{1,15}$)|(^[А-Я]?[а-я]{1,15}$)', '');
            break;
                
        case 'login':
            pattern =  new RegExp('(^[a-zA-Z0-9]{3,15}$)', '');
            break;
                
        case 'password':
            pattern =  new RegExp('^[a-zA-Z0-9_\\*\\!\\^]{6,15}$', '');
            break;
            
        case 'email':
            pattern =  new RegExp('^[a-z0-9\\.]{3,25}@[a-z\\.]{3,10}\\.{1}[a-z]{2,5}$', '');
            break;
            
        case 'phone':
            if(document.getElementById(formID + 'Form').value == '') {
                document.getElementById(formID).style.color = 'green';
                return;
            }
            pattern =  new RegExp('^(\\+\\d{3}\\s\\d{2})?\\s?\\d{3}(-|\\s)?\\d{2}(-|\\s)?\\d{2}$', '');
            break;
                
        case 'address':
            pattern = new RegExp('^[A-Za-zА-Яа-я\\d\\s\\.,\\-]{1,250}$','');
            break;
    }
        
    inputStr = document.getElementById(formID + 'Form').value;
        
    if(pattern.test(inputStr)) {
        document.getElementById(formID).style.color = 'green';
    } else {
        document.getElementById(formID).style.color = 'red';
    }
}

function clearField(msg,formID) {
    var str = document.getElementById(formID).value;
    if(str == msg)
        document.getElementById(formID).value='';
}

function restoreField(msg,formID) {
    var str = document.getElementById(formID).value;
    if(str == '')
        document.getElementById(formID).value=msg;
}

