<template>
    <header>
        <nav class="navbar navbar-expand-lg bg-light mb-3">
            <div class="container">
                <a class="navbar-brand" href="#">FOTOALBUM</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-item nav-link active fw-bold" href="#">Home</a>
                        <a class="nav-item nav-link" href="http://localhost:8080/">Backoffice</a>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</template>

<script>
export default {
    name: 'HeaderWeb',
    data() {
        return {
            
        }
    }
}
</script>

<style lang="scss" scoped>

</style>