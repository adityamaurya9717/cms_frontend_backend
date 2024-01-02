const endPoint = {
    cms:'http://localhost:8080/api/'
}

const path = {
    cms:{
        adduser:'customer/show-user',
        getuser:'customer/get-user',
        deleteuser:'customer/delete-user',
        getAllBrands:'brand/get-allbrand',
        addbrand:'brand/add-brand',
        getBrands:'brand/get-brand',
        updateBrand:'brand/update-brand',

        getALLCategory:'category/get-all-category-bylevel',
        getCategory:'category/get-category',
        addcategory:'category/add-category',

        uploadFile : 'upload/file',

        getProduct :'product/get-product',
        addProduct : 'product/add-product',
        updateProduct: 'product/update-product',

        login : 'public/login',
        role : 'role/roles'

    }
}

export {endPoint,path}