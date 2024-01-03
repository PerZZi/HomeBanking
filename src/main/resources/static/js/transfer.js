const { createApp } = Vue

let app = createApp({
  data() {
    return {
      clients: [],
      accounts: [],
      amount: "",
      description: "",
      accountOrigen: "",
      accountDestino: "",
    }
  },

  created() {
    this.loanData()
  },
  methods: {
    loanData() {
      axios.get("/api/clients/current")
        .then(response => {
          this.clients = response.data
          console.log(this.clients)
          this.accounts = response.data.accounts
          console.log(this.accounts)
        })
        .catch(error => console.log(error))
    },

    createTransfer() {
      axios.post("/api/transactions?amount=" + this.amount + "&description=" + this.description + "&accountOrigin=" + this.accountOrigen + "&accountDestination=" + this.accountDestino)
        .then(response => {
          console.log(response)
          window.location.href = "/web/accounts.html"
        })
        .catch(error => console.log(error))
    },
  }
}).mount('#app')