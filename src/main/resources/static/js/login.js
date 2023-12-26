const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      name: "",
      lastName: "",
      email: "",
      password : ""
    }
  },

  created() {
  },
  methods: {
    login(){
      axios.post("/api/login?email=" + this.email + "&password=" + this.password)
      .then(response => {
        console.log(response)
        window.location.href="/web/accounts.html"
      })
      .catch(response => console.log(response))
    },
    getName(event){
      this.name = event.target.value
      console.log("Name", this.name)
  },
  getLastName(event){
    this.lastName = event.target.value
    console.log("LastName", this.lastName)
},

    getEmail(event){
      this.email = event.target.value
      console.log("Email", this.email)
  },

  getPassword(event){
      this.password = event.target.value
      console.log("password" , this.password)
  },

    register(){
      axios.post("/api/clients?name="+ this.name + "&lastName=" + this.lastName + "&email=" + this.email + "&password=" + this.password)
      .then(response => {
        console.log(response)
      })
      .catch(response => console.log(response))
    }
  }

}).mount('#app')