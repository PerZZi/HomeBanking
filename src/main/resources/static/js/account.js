const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data : [],
      transactions: [],
      id : null
    }
  },

  created() {
    const search = location.search
    const params = new URLSearchParams(search)
    this.id = params.get('id')
    console.log(this.id)
    this.loadData()
    this.formatBudget()
  },
  methods: {
    loadData() {
      axios.get("/api/accounts/" + this.id + "/transactions")
        .then(response => {
          this.data = response.data
          console.log(this.data)
          this.transactions = response.data
          console.log(this.transactions)
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

    deleteClient() {
      axios.delete("/api/accounts/current/transaction")
        .then(response => {
          console.log(response)
        })
        .catch(error => console.log(error))
    }

  }
}).mount('#app')