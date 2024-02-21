const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      name: "",
      lastName: "",
      email: "",
      password: "",
      email1: "",
      password1: "",
      currentForm: 'registerForm'
    }
  },

  created() {
  },
  methods: {
    login() {
      axios.post("/api/login?email=" + this.email1 + "&password=" + this.password1)
        .then(response => {
          console.log(response)
          this.loginAlert()
          setTimeout(() => {
            window.location.href = "/web/accounts.html"
          }, 1700)
        })
        .catch(response => {
          console.log(response)
          this.loginErrorAlert()
        }
        )
    },
    getName(event) {
      this.name = event.target.value
      console.log("Name", this.name)
    },
    getLastName(event) {
      this.lastName = event.target.value
      console.log("LastName", this.lastName)
    },

    getEmail(event) {
      this.email = event.target.value
      console.log("Email", this.email)
    },

    getPassword(event) {
      this.password = event.target.value
      console.log("password", this.password)
    },
    getEmailLogin(event) {
      this.email1 = event.target.value
      console.log("Email", this.email)
    },

    getPasswordLogin(event) {
      this.password1 = event.target.value
      console.log("password", this.password)
    },

    register() {
      const createClient = {
        "name": this.name,
        "lastName": this.lastName,
        "email": this.email,
        "password": this.password
      }
      axios.post("/api/clients", createClient)
        .then(response => {
          console.log(response)
          this.clearFields();
          this.registerAlert();
          setTimeout(() => {
            
          }, 1700)
          // alert("Registro exitoso. ¡Bienvenido!");
        })
        .catch(response => console.log(response))
    },
    clearFields() {
      this.name = "";
      this.lastName = "";
      this.email = "";
      this.password = "";
    },

    loginAlert() {
      Swal.fire({
        position: "top-center",
        icon: "success",
        title: "Successful Login",
        showConfirmButton: false,
      });
    },

    loginErrorAlert() {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Something went wrong!",
        footer: '<a href="#">Why do I have this issue?</a>'
      });
    },

    registerAlert() {
      Swal.fire({
        position: "top-center",
        icon: "success",
        title: "Successful Registration",
        showConfirmButton: false,
      });
    },

    registerErrorAlert() {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "Something went wrong!",
        footer: '<a href="#">Why do I have this issue?</a>'
      });
    },

    toggleForm(event) {

      const loginButton = document.querySelector(".login");
      const registerButton = document.querySelector(".register");

      if (event.target == loginButton) {
        this.currentForm = 'loginForm';
      } else if (event.target == registerButton) {
        this.currentForm = 'registerForm';
      }
    },

  }

}).mount('#app')