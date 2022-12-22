<template>
    <div class="mt-4 container">
        <div class="row">

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
                                <h6>Categorie: </h6>
                                <span class="d-inline-block me-1 text-primary" v-for="category in photo.categories" :key="category.id">#{{category.name}} </span>
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