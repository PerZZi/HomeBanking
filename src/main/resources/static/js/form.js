const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      name: "",
      lastName: "",
      email: ""
    }
  },

  created() {
    this.loadData()
  },
  methods: {
    loadData() {
      axios.get("/clientes")
        .then(response => {
          this.data = response.data._embedded.clientes
          console.log(this.data)
        })
        .catch(error => console.log(error))
    },

    createClient(event) {
      event.preventDefault()
      const nuevoCliente = {
        "name": this.name,
        "lastName": this.lastName,
        "email": this.email
      }
      axios.post("/clientes", nuevoCliente)
        
      
          .then(response => {
            this.data = response
            this.loadData()
          })
          .catch(error => console.log(error))
    }
  }

}).mount('#app')