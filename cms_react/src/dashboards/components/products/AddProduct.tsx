import React,{useState,useReducer} from 'react';
import Box from '@mui/material/Box'
import TextField from '@mui/material/TextField'
import { Button, FormControl, MenuItem, Select, SelectChangeEvent } from '@mui/material';
import InputLabel from '@mui/material/InputLabel';


const AddProduct = () => {
    console.log("ADD PRoduct")
    const dialogdata = {
      message:"success Fully added"
    }
    const getCategory = () =>{
        return ['asas','sasas','sasas']
    }

    const input = {
        productName :'',
        productCategory:'',
        productBrand:''
    }
    const [productDetail,setProductDetail] = useState(input)
    
    const setProductName = (e:any)=>{setProductDetail(preState=>{return {...preState, productName:e.target.value}})}
    const setProductCategory = (e:any)=>{setProductDetail(preState=>{  
            return {...preState, productCategory:e.target.value}}
            )}

    
       
    const onSubmit = ()=>{
        console.log(productDetail)
    }
    

    return (
        <div>
            <h3>Product Catalog</h3>
            <Box component="form" sx={boxStyle}>
                <div>
                    <TextField onChange={setProductName} label="Product Name" variant="outlined"></TextField>
                </div>
                <div>
                    <TextField label="Product Name" variant="outlined"></TextField>
                </div>
                <div>
                    <TextField label="Product Name" variant="outlined"></TextField>
                </div>
                <div>
                    <FormControl  sx={{ m: 0, minWidth: 250 }}>
                        <InputLabel  id="demo-simple-select-autowidth-label">Product Brand</InputLabel>
                        <Select
                            labelId="demo-simple-select-autowidth-label"
                            id="demo-simple-select-autowidth"
                            autoWidth
                            label="Product Brand"
                        >
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>
                            <MenuItem value={10}>Samsung</MenuItem>
                            <MenuItem value={21}> MI</MenuItem>
                            <MenuItem value={22}>Apple</MenuItem>
                        </Select>
                    </FormControl>
                </div>
                <div>
                    <FormControl sx={{ m: 0, minWidth: 250 }}>
                        <InputLabel id="demo-simple-select-autowidth-label">Product Category</InputLabel>
                        <Select
                            onChange={setProductCategory}
                            value={productDetail.productCategory}
                            labelId="demo-simple-select-autowidth-label"
                            id="demo-simple-select-autowidth"
                            autoWidth
                            label="Product Category"
                        >
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>
                           {
                             getCategory().map(data=>{
                                    return  <MenuItem value={22}>{data}</MenuItem>
                                })
                            }
                        </Select>
                    </FormControl>
                </div>
                <div>

                </div>
            </Box>
            <Box component="div" sx={boxSubmitStyle}>
               <Button onClick={onSubmit} variant="contained">Submit</Button>
               <Button variant="contained">Reset</Button>


            </Box>
        </div>
    )
}
const boxStyle = {
    width: '80%',
    margin: '40px auto',
    display: 'grid',
    gridTemplateColumns: '33% 33% 33%',
    rowGap: '50px'
}
const boxSubmitStyle = {
    width: '50%',
    margin: '40px auto',
    display: 'grid',
    gridTemplateColumns: '50% 50%',
    columnGap: '40px'

}
export default AddProduct;