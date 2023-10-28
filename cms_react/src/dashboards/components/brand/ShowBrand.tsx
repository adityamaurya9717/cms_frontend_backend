
import { Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableFooter, TableHead, TablePagination, TableRow } from '@mui/material'
import TablePaginationActions from '@mui/material/TablePagination/TablePaginationActions'
import React,{useState,useEffect} from 'react'
import { endPoint, path } from '../../../constant/EndPoint'
import axios from 'axios'


type BrandListInterface={
  brandId: string | null | undefined
  brandName : string | null | undefined
  brandDescription : string | null | undefined  
}

let brandResponse:Array<BrandListInterface> = [
  {
   brandId : 'dummy ',
   brandName:'dsds',
   brandDescription:'dsds'
  }
]
let columns:Array<string> = ['BrandId','BrandName','Brand Description','Actions']
 
function ShowBrand(props:any) {
  const [loading, setLoading] = React.useState<boolean>(true);

  // set initial PageNo=0
  const [page, setPage] = React.useState<number>(0);
  // set RowPerPageNo = 5
  const [rowsPerPage, setRowsPerPage] = React.useState<number>(5);
  const [brandList, setBrandList] = React.useState<Array<BrandListInterface>>(brandResponse);
  const [totalCount, setToalCount] = React.useState<number>(0);


  const handleChangePage = (
    event: React.MouseEvent<HTMLButtonElement> | null,
    newPage: number,
  ) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
  ) => {
    console.log("handle Row Per PAge=",parseInt(event.target.value))
    setRowsPerPage(parseInt(event.target.value));
    setPage(1);
  };

  useEffect(()=>{
    let url = endPoint.cms + path.cms.getBrands
    let requestPayload = {

      brandName: '',
      description: '',
      active:true,
      pageNo:page,
      size:rowsPerPage
    }
    axios.post(url,requestPayload,{})
    .then(res=>{
      brandResponse = [...res.data.brandList];
      console.log(brandResponse)
      setBrandList([...brandResponse]);
      setToalCount(res.data.totalCount)
      setLoading(false)
    })
    .catch(err=>{
      console.log(err)
    })

  },[page,rowsPerPage])
   
  // update brand
  const updateBrandHandler = (rowData:any)=>{
    props.onUpdateBrand(rowData);
  }


   if(loading==false) { return  (
    <Paper sx={{ width: '100%', overflow: 'hidden' }}>
            <h1>Brands List</h1>

    <TableContainer sx={{ maxHeight: 440 }} style={{width:'100%',paddingTop:'10px'}} >
      <Table stickyHeader  style={{width:'80%',margin:'0 auto'}} aria-label="sticky table">
        
        <TableHead>
          <TableRow>
              {
                columns.map((col,index)=>{
                  return <TableCell style={{fontSize:'20px'}} variant='head' key={index}> <b>{col}</b></TableCell>
                })
              }
          </TableRow>
        </TableHead>

        <TableBody>
          {
            brandList.map((row,index)=>(
              <TableRow key={index}>
                <TableCell typeof='input'>{row.brandId}</TableCell>
                <TableCell>{row.brandName}</TableCell>
                <TableCell>{row.brandDescription}</TableCell>
                <TableCell><Button onClick={(e)=>{updateBrandHandler(row)}} >Edit</Button></TableCell>
              </TableRow>
            ) ) 
          }

        </TableBody>

        {/* <TableFooter>
          <TableRow>
            <TablePagination
             component={Box}
             rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
             colSpan={brandResponse.length}
             count={17}
             rowsPerPage={rowsPerPage}
             page={page}
             SelectProps={{
               inputProps: {
                 'aria-label': 'rows per page',
               },
               native: false,
             }}
             onPageChange={handleChangePage}
             onRowsPerPageChange={handleChangeRowsPerPage}
            >

            </TablePagination>
          </TableRow>
        </TableFooter> */}

        
      </Table>

    </TableContainer>
    <TablePagination
             style={{margin:'0 auto',width:'50%'}}
             component={Box}
             rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
             colSpan={brandResponse.length}
             count={totalCount}
             rowsPerPage={rowsPerPage}
             page={page}
             SelectProps={{
               inputProps: {
                 'aria-label': 'rows per page',
               },
               native: false,
             }}
             onPageChange={handleChangePage}
             onRowsPerPageChange={handleChangeRowsPerPage}
            >

            </TablePagination>
    </Paper>
  ) }
  else{
    return <h1>Loading</h1>
  }
}

export default ShowBrand
