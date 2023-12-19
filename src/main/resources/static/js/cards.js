const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      cards : []
    }
  },

  created() {
    this.loadData()
    this.formatBudget()
  },
  methods: {
   loadData(){
    axios.get("/api/clients/1")
    .then(response =>{
      this.data = response.data
      console.log(this.data)
      this.cards = this.data.cards
      console.log(this.cards)
    })
    .catch(error => console.log(error))
   },
   formatBudget(balance) {
    if (balance !== undefined && balance !== null) {
        return balance.toLocaleString("en-US", {
            style: "currency",
            currency: "ARS",
            minimumFractingDigits: 0,
        })
    }
},

   deleteClient(){
    axios.delete("/api/clients/1")
    .then(response => {
      console.log(response)
    })
    .catch(error => console.log(error))
   }

  }
}).mount('#app')