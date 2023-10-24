import React, { lazy, useState,useMemo } from 'react';
import './sidebar.css'

import NavigationBar from './NavigationBar';
import SideBar from './SideBar';
import { Route, Routes,useNavigate,redirect } from 'react-router-dom';
import AddUser from '../../dashboards/components/AddUser';
import Product from '../../dashboards/components/products/AddProduct';
import ShowUser from '../../dashboards/components/ShowUser';
import ProductDashBoard from '../../dashboards/components/products/ProductDashBoard';
import AddProduct from '../../dashboards/components/products/AddProduct';
import ShowProduct from '../../dashboards/components/products/ShowProduct';
import Brand from '../../dashboards/components/brand/Brand';
const UserDashboard = lazy(() => import("../../dashboards/components/UserDashBoard"))

const MainComponent = () => {
    const [showSidebar, setSideBar] = useState(true);
    const navigationBtnHandler = () => {
        console.log("toggle slidebar")
        setSideBar(preState => !preState)
    }
    const navigate = useNavigate()
    const sideBar: boolean = showSidebar ;

    return (
        <div style={{ boxSizing: 'border-box' }} >
            <NavigationBar onSideBar={navigationBtnHandler} />

            <div className={sideBar == true ? "container" : "container_two"} >
                <SideBar showSidebar={showSidebar} />
                <div className="content">

                    <Routes>
                        <Route path="userdasboard" element={
                            <React.Suspense fallback={<>...Loading User Dashboard</>}>
                                <UserDashboard  />
                            </React.Suspense>
                        }>   
                            <Route path="add-user" element={<AddUser />}></Route>
                                <Route path="show-user"   element={<ShowUser />}></Route>  
                                   

                        </Route>
                        <Route path="product"  element={<ProductDashBoard />} >
                            <Route path="add-product" element={<AddProduct />}></Route>
                            <Route path="show-product" element={<ShowProduct />}></Route>
                        </Route>
                        <Route path="category" element={<Brand />} />
                        <Route path="brand" element={<Brand />} />

                    </Routes>


                </div>


            </div>

        </div>)



}

export default MainComponent;

