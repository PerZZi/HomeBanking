const { createApp } = Vue

let app = createApp({
  data() {
    return {
      transactions: [],
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
      axios.get("/api/accounts/"+ this.id +"/transactions")
        .then(response => {
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
      axios.delete("/api/accounts/1/transactions")
        .then(response => {
          console.log(response)
        })
        .catch(error => console.log(error))
    }

  }
}).mount('#app')