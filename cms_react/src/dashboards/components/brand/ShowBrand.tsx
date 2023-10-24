
import { Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableFooter, TableHead, TablePagination, TableRow } from '@mui/material'
import TablePaginationActions from '@mui/material/TablePagination/TablePaginationActions'
import React,{useState,useEffect} from 'react'


type BrandListInterface={
  brandId: string | null | undefined
  brandName : string | null | undefined
  brandDescription : string | null | undefined  
}

let brandResponse:Array<BrandListInterface> = [
  {
   brandId : 'sdsd',
   brandName:'dsds',
   brandDescription:'dsds'
  }
]
let columns:Array<string> = ['BrandId','BrandName','Brand Description','Actions']
 
function ShowBrand() {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const handleChangePage = (
    event: React.MouseEvent<HTMLButtonElement> | null,
    newPage: number,
  ) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
  ) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };



  return (
    <TableContainer style={{width:'100%',paddingTop:'10px'}} component={Paper}>
      <h1>Brands List</h1>
      <Table style={{width:'80%',margin:'0 auto'}} aria-label="simple table">
        
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
            brandResponse.map((row,index)=>(
              <TableRow key={index}>
                <TableCell typeof='input'>{row.brandId}</TableCell>
                <TableCell>{row.brandName}</TableCell>
                <TableCell>{row.brandDescription}</TableCell>
                <TableCell><Button>Edit</Button></TableCell>
              </TableRow>
            ) ) 
          }

        </TableBody>

        <TableFooter>
          <TableRow>
            <TablePagination
             component={Box}
             rowsPerPageOptions={[5, 10, 25, { label: 'All', value: -1 }]}
             colSpan={brandResponse.length}
             count={columns.length}
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
        </TableFooter>

        
      </Table>

    </TableContainer>
  )
}

export default ShowBrand
