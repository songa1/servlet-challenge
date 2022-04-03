const sex = document.querySelectorAll('input[name="gender"]');
let selectedValue;
for (const rb of sex) {
  if (rb.checked) {
    selectedValue = rb.value;
    break;
  }
}
const roles = document.querySelectorAll('input[name="role"]');
let selectedValue;
for (const role of roles) {
  if (rb.checked) {
    selectedValue = role.value;
    break;
  }
}

