const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      cards: [],
      type: "",
      color:""
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
      axios.post("/api/clients/current/cards?type=" + this.type+ "&colorType=" + this.color)
        .then(response => {
            console.log(response)
            this.cardAlert()
            setTimeout(() => {
              window.location.href = "/web/cards.html"
            }, 1700) 
          })
        .catch(error => console.log(error))
    },
    cardAlert() {
      Swal.fire({
        position: "top-center",
        icon: "success",
        title: "Successful Login",
        showConfirmButton: false,
      });
    },
}
}).mount('#app')