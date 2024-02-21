const { createApp } = Vue

let app = createApp({
  data() {
    return {
      data: [],
      cards: [],
      type: "",
      color:"",
      cardNumber:null,
      numCards: 0
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
          this.numCards = this.cards.length
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
        .then(response => console.log(response))
        .catch(error => console.log(error))
    },

    deleteCard(cardNumber){
      axios.patch("/api/clients/current/cards/delete?id=" + cardNumber)
      .then(response => {
        console.log(response)
        this.cardDeleteAlert()
        setTimeout(() => {
          window.location.href="/web/cards.html"
        }, 1700)
      })
      .catch(error => console.log(error))
    },

    deleteClient() {
      axios.delete("/api/clients/current")
        .then(response => {
          this.loadData()
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
    },
    cardDeleteAlert(){
      Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire({
            title: "Deleted!",
            text: "Your file has been deleted.",
            icon: "success"
          });
        }
      });
    }

  }
}).mount('#app')