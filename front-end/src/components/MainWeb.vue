<template>
    <div class="mt-3 container">
        
        <div class="row">

            <div class="col-12 d-flex mb-3">
                <h6 class="text-primary mb-2">Inserisci un titolo o un tag per effettuare una ricerca</h6>
                <form class="w-100" id="search_bar">
                    <div class="input-group">
                    <input type="text" class="form-control" v-model="searchValue" @keyup="getFilteredPhotos()" placeholder="premendo INVIO la ricerca si resetterà" aria-label="Search" aria-describedby="basic-addon1">
                    <span class="btn btn-success" id="basic-addon1" @click="getFilteredPhotos()">Cerca</span>
                    </div>
                </form>
            </div>

            <div class="col-2" v-for="photo in photos"  :key="photo.id" :class="photo.visible ? ' ' : 'd-none'" 
            @click="setActivePhotoIndex(getIndexFromPhotoId(photo.id)), getPhotoCategories(photo.id)">
                
                <div class="card" :class="photo.visible ? ' ' : 'd-none'">
                    <div v-if="photo.visible">
                        <img :src="photo.url" class="img-fluid" alt="photo-not-valid">
                        <div class="card-body">
                            <h6 class="card-title">{{photo.title}}</h6>
                            <p class="fw-light"> 
                            <span class="badge rounded-pill text-bg-primary">#{{photo.tag}}</span>
                            </p>
                            
                            <div v-if="activePhotoIndex === getIndexFromPhotoId(photo.id)" class="pb-3">
                                <h6>Descrizione:</h6> <span class="fst-italic fw-light"> {{photo.description}} </span>
                                <div v-if="photo.categories != null">
                                    <h6 class="mt-2">Categorie: </h6>
                                    <span class="d-inline-block me-1 text-primary" v-for="category in photo.categories" :key="category.id">#{{category.name}} </span>
                                </div>
                                <div v-if="photo.comments != null" class="mt-2">
                                    <h6 class="d-inline-block mb-1">Commenti: </h6>
                                    <ul>
                                        <li v-for="comment in photo.comments" :key="comment.id">
                                            <span>{{comment.text}}</span>
                                        </li>
                                    </ul>
                                    <div class="fst-italic mb-1">scrivi un commento:</div>
                                    <div class="d-flex align-items-center mb-2">
                                    <input type="text" class="form-control" v-model="userComment">
                                    <button class="btn btn-sm btn-success" @click="postComment(photo.id)">Invia</button>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

        </div>
    </div>
</template>

<script>
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/1/';
const ACTIVE_PHOTO_INDEX = -1;

export default {
    name: 'MainWeb',

    data() {
        return {
            photos: [],
            activePhotoIndex: ACTIVE_PHOTO_INDEX,
            userComment: '',
            newComment: {text: '', photo: ''},
            searchValue: '',
        }
    },

    methods: {
        getPhotos() {
            axios.get(API_URL + 'photos/all')
            .then(response => {
                this.photos = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        },

        getPhotoCategories(photoId){
            axios.get(API_URL + 'categories/by/photos/' + photoId)
                .then(response => {
                    const photoCategories = response.data;
                    if (photoCategories == null) return
                    
                    const index = this.getIndexFromPhotoId(photoId);
                    this.photos[index].categories = photoCategories;
                    console.log(photoCategories);
                })
                .catch(error => {
                console.log(error);
                });
        },

        getPhotoComments(photoId) {
        axios.get(API_URL + 'comments/by/photos/' + photoId)
            .then(response => {
                const photoComments = response.data;
                if (photoComments == null) return
                const index = this.getIndexFromPhotoId(photoId);
                this.photos[index].comments = photoComments;
            })
            .catch(error => {
                console.log(error);
            });
        },

        postComment(photoId){
        this.newComment.photo = photoId;
        this.newComment.text = this.userComment;
        axios.post(API_URL + 'comments/add/' + photoId , this.newComment)
            .then(response => {
            this.getPhotoComments(photoId);
            this.userComment = '';
            console.log(response);
        })
        .catch(error => {
          console.log(error);
        });
    },

        getFilteredPhotos(){
        if(this.searchValue === '') return this.getPhotos();
            axios.get(API_URL + 'photos/search/' + this.searchValue)
                .then(response => {
                this.photos = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        },

        setActivePhotoIndex(index){
            this.activePhotoIndex = index;
        },

        getIndexFromPhotoId(id){
            return this.photos.findIndex(photo => photo.id === id);
        },
    },

    mounted() {
        this.getPhotos();
    }
}
</script>

<style lang="scss" scoped>

</style>