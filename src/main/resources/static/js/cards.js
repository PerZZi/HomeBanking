const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      cards: []
    }
  },

  created() {
    this.loadData()
    this.formatBudget()
  },
  methods: {
    loadData() {
      axios.get("/api/clients/current")
        .then(response => {
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

    createCard() {
      axios.post("/api/clients/current/cards?colors=" + this.selectedColor + "&cardType=" + this.selectedType)
        .then(response => console.log(response))
        .catch(error => console.log(error))
    },

    deleteClient() {
      axios.delete("/api/clients/current")
        .then(response => {
          console.log(response)
        })
        .catch(error => console.log(error))
    },
    logout() {
      axios.post("/api/logout")
        .then(response => {
          console.log(response)
          if (response.status == 200) {
            window.location.href = "../index.html"
          }
        })
    }

  }
}).mount('#app')