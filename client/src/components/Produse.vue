<template>
    <div class="Produse">
        <SideBar></SideBar>

        <button class="button is-info is-rounded w3-hover-shadow" width="300px"
            style="font-size:18px; margin-left:50px; margin-top:20px; margin-bottom:20px;"
            @click="openModalAddProdus">Adauga produs</button>
        <form class="example" style="margin-left:850px; margin-top:-70px;max-width:300px; margin-bottom:20px;">
            <input type="text" placeholder="Search.." name="search2">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
        <div id="modalProgramari" class="modal">
            <div class="modal-content">
                <span class="close" @click="closeModalProgramari">&times;</span>
                <p>Some text in the Modal..</p>
            </div>

        </div>
        <div id="modalProdus" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <span class="close" @click="closeModal">&times;</span>
                
                

                    <label for="lname">Detalii produs</label>

                    <input type="text" id="numeProdus" name="lastname" placeholder="Nume produs" style="margin-bottom:20px; ">
                    <input type="number" id="pretProdus" name="lastname" placeholder="Pret produs" style="margin-bottom:20px;">
                    <input type="text" id="cantitateProdus" name="lastname" placeholder="Cantitate produs" style="margin-bottom:20px;">

                    <input type="file" id="myFile" name="filename">
                   

                    <button id="btnAdauga" @click="adaugaProdus()">Adauga produs</button>
                
            </div>

        </div>

        <div id="modalEditProdus" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <span class="close" @click="closeModalEdit">&times;</span>
                
                

                    <label for="lname">Detalii produs</label>

                    <input type="text" id="EditnumeProdus" name="lastname" placeholder="Nume produs" style="margin-bottom:20px; ">
                    <input type="number" id="EditpretProdus" name="lastname" placeholder="Pret produs" style="margin-bottom:20px;">
                    <input type="text" id="EditcantitateProdus" name="lastname" placeholder="Cantitate produs" style="margin-bottom:20px;">

                    <input type="file" id="myFile" name="filename">
                   

                    <button id="btnEdit">Editeaza produs</button>
                
            </div>

        </div>
        <div class="w3-row w3-border">
            <div class="w3-quarter w3-container" v-for="(produs,index) in produse" v-bind:key="produs.id">
                <v-card class="mx-auto" max-width="374" style="margin-top:20px" :id="'produs' + index" max-height:120px>
                    <v-card-text >
                        <h4 class="title" style="text-align:center">{{produs.nume}} </h4>
                        <div class="text--primary ">
                            <img :src="require(`../pics/${produs.photo}`)" width="110px" style="margin-left:60px; cursor: pointer;">
                        
                        </div>
                        <h4 class="title" style="margin-left:70px">{{produs.pret}} lei  / {{produs.cantitate}} </h4>
                       Nota:  <input  :v-model="calculateMedieNote(produs)" v-bind:placeholder="calculateMedieNote(produs)" readonly/>

                    </v-card-text>
                    <v-card-actions  >
                     
 
                        <img src="../assets/editing.png" width="30px" style="margin-left:80px; cursor: pointer;" @click="openModalProdus(produs)">
                        <img src="../assets/trash.png" width="30px" style="margin-left:10px; cursor: pointer;" @click="deleteProdus(produs)">
                       
                             </v-card-actions>


                </v-card>
            </div>

        </div>
    </div>
</template>

<script>
import SideBar from "./SideBar";
import axios from "axios"


export default {
    name: 'Produse',
    components: { SideBar },
    data() {
        return {
            produse: [{ id: 1, nume: "Ion", prenume: "Elena", varsta: 70, photo: "" },
            { id: 2, nume: "Samsung Galaxy A20", varsta: 20, photo: "" },
            { id: 12, nume: "Ion", prenume: "Elena", varsta: 20, photo: "" },
            { id: 22, nume: "Ion", prenume: "Elena", varsta: 20, photo: "" },
            { id: 32, nume: "Ion", prenume: "Elena", varsta: 20 },
            { id: 42, nume: "Ion", prenume: "Elena", varsta: 20 },
            { id: 52, nume: "Ion", prenume: "Elena", varsta: 20 },
            { id: 62, nume: "Ion", prenume: "Maria", varsta: 20 },
            { id: 72, nume: "Ion", prenume: "Vasilica", varsta: 60 },
            {
                id: 3,
                nume: "Ion",
                prenume: "Elena",
                varsta: 20
            }
            ],
            revealProdusrId: -1,
            reveal: false
        };
    },
    methods: {
        // getImgUrl(produs) {
        //     return require(  produs.photo)
        // },
        displayInfoProdus(produs) {
            this.revealProdusId = produs.id

        },
        openModalAddProdus() {
            var modal = document.getElementById("modalProdus");
            modal.style.display = "block";
        },
        closeModal() {
            var modal = document.getElementById("modalProdus");
            modal.style.display = "none";
        },
        closeModalEdit(){
            var modal = document.getElementById("modalEditProdus");
            modal.style.display = "none";
        },
        openModalProgramari() {
            var modal = document.getElementById("modalProgramari");
            modal.style.display = "block";
        },
        closeModalProgramari() {
            var modal = document.getElementById("modalProgramari");
            modal.style.display = "none";
        },
      
        calculateMedieNote( produs){
            if(produs.note==null) return "Nicio nota"
            if(produs.note.length ==0 ) return "Nicio nota"
                let sum=0;
                for(let i=0;i<produs.note.length;i++){
                    sum+=produs.note[i]
                }
                return sum/produs.note.length
        },
        closeModaEdit() {
            var modal = document.getElementById("EditmodalProdus");
            modal.style.display = "none";
        },
        openModalProdus(produs){
            //editare produs
            var modal = document.getElementById("modalEditProdus");
            document.getElementById("EditnumeProdus").value = produs.nume
            document.getElementById("EditpretProdus").value = produs.pret;
            document.getElementById("EditcantitateProdus").value= produs.cantitate
           document.getElementById("btnEdit").addEventListener("click",()=>{
            console.log(produs)
            //luare valore si editare
            let numeProdus = document.getElementById("EditnumeProdus").value
            let pretProdus = document.getElementById("EditpretProdus").value

            let cantitateProdus = document.getElementById("EditcantitateProdus").value
        
            let srcPhoto= document.getElementById("myFile").value.split("\\")
            let pozaSrc = srcPhoto[srcPhoto.length-1]
           if(pozaSrc == '') pozaSrc = produs.photo
            let produsNou = {
                codProdus: produs.codProdus,
                nume: numeProdus,
                pret: pretProdus,
                cantitate: cantitateProdus,
                photo: pozaSrc
            }
            axios.put(`http://localhost:8000/produse/${produs.codProdus}`, produsNou).then(() => {
				alert("Produs actualizat");
				axios.get("http://localhost:8000/produse").then((response) => {
					this.produse = response.data;
				
				});
            })
           })
            modal.style.display = "block";
        },
        deleteProdus(produs){
            axios.delete(`http://localhost:8000/produse/${produs.codProdus}`).then(() => {
				alert("Produs sters");
				axios.get("http://localhost:8000/produse").then((response) => {
					this.produse = response.data;

				
				});
            })
        },
      
        adaugaProdus(){
            let numeProdus = document.getElementById("numeProdus").value
            let pretProdus = document.getElementById("pretProdus").value

            let cantitateProdus = document.getElementById("cantitateProdus").value
        
            console.log(document.getElementById("myFile").value)
            let srcPhoto= document.getElementById("myFile").value.split("\\")
            console.log(srcPhoto)
            let pozaSrc = srcPhoto[srcPhoto.length-1]
            let produs = {
                nume: numeProdus,
                pret: pretProdus,
                cantitate: cantitateProdus,
                photo: pozaSrc
            }
            axios.post("http://localhost:8000/produse", produs).then(() => {
				alert("Produs adaugat");
				axios.get("http://localhost:8000/produse").then((response) => {
					this.produse = response.data;
				
				});
            })
             

        }
    },
    mounted() {

        axios.get("http://localhost:8000/produse").then((response) => {
            console.log("fne")
            console.log(response.data)
            this.produse = response.data;


        });
        // regasire interviuri

    },
    created() {
        if (localStorage.getItem("tokenAngajat") === null) {
            this.$router.push("/angajat/auth");
        }
    },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style scoped>
#btnAdd {
    margin-top: 30px;
    margin-left: -920px;

}
.w3-border {
    border: 1px solid #ffffff!important;
}
.mx-auto{
    height: 432px;
}
.v-card > *:last-child:not(.v-btn):not(.v-chip):not(.v-avatar) {
    border-bottom-left-radius: inherit;
    border-bottom-right-radius: inherit;
    margin-top: 10px;
   
}
.card-container {
    width: 100%;
    height: auto;
    margin: auto 0;
    display: flex;
    justify-content: center;
}
.card-container ul {
    text-align: center;
    margin: auto 0;

}

.card-container li {
    list-style-type: none;
    padding: .5em .5em .5em .5em;
    text-align: left;
}

.card {
    transition: 0.2s;
    overflow: hidden;
    width: 7em;
    word-wrap: break-word;
}

.card-image {
    box-shadow: 1 1 rgb(206, 192, 192);
    transition: .3s;
    background-color: #C33;
    margin: auto 0;
    text-align: center;
}

.title {
    text: center;
    font-size: 18px;

    justify-content: center;
}

.card-image:hover {
    transform: scale(1.25, 1.25);
    transform-origin: 50% 50%;
    box-shadow: 2 2 #0f0;
}

.v-card--reveal {
    bottom: 0;
    opacity: 1 !important;
    position: absolute;
    width: 100%;
}

.modal {
    display: none;
    position: fixed;
    z-index: 1;
    padding-top: 100px;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0, 0, 0);
    background-color: rgba(0, 0, 0, 0.4);

}

.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid rgb(255, 252, 252);
    width: 50%;
}

.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

input[type=text],
select {
    width: 100%;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

form.example input[type=text] {
    padding: 10px;
    border: 1px solid grey;
    float: left;
    width: 80%;
    background: #f1f1f1;
}

form.example button {
    float: left;
    width: 20%;
    padding: 8px;
    background: #2196F3;
    color: white;
    font-size: 17px;
    border: 1px solid grey;
    border-left: none;
    cursor: pointer;
}

form.example button:hover {
    background: #0b7dda;
}

form.example::after {
    content: "";
    clear: both;
    display: table;
}
</style>
