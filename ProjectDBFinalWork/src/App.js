import logo from "./logo.svg";
import Login from "./components/Login";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import { useGlobalContext } from "./context";
import Abc from "./components/Abc";
import Box from "./components/Box";
import NavBarBox from "./components/NavBarBox";
import Home2 from "./components/Home2";
import NavBarClass from "./components/NavBarClass";
import ClassStructure from "./components/ClassStructure";
import ClassWork from "./components/ClassWork";
import People from "./components/People";
import Assignment from "./components/Assignment";
import Signup from "./components/Signup";
import Stream from "./components/Stream";
import People2 from "./components/People2";
import Outlet2 from "./components/Outlet2.";
import SubmissionOutlet from "./components/SubmissionOutlet";
import Submission from "./components/Submission";
import ViewAllAssignment from "./components/ViewAllAssignment";
import ViewAllAssignmentOutlet from "./components/ViewAllAssignmentOutlet";
import DisplayAassignment from "./components/DisplayAassignment";
function App() {
  const { Session, loginstate } = useGlobalContext();

  return (
    <>

        <Router>
          <Routes>
            <Route index element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/Home" element={<Home2 />} />

            {/* <Route exact path="/class/:id" element={<ClassStructure/>}/>  */}
            <Route path="class" element={<ClassStructure />}>
              <Route path=":id" element={<People2 />}>
                <Route index element={<Stream />} />
                <Route path="people" element={<People />} />
                <Route path="classwork" element={<Outlet2 />}>
                  <Route index element={<ClassWork />} />
                  <Route path=":id1" element={<Assignment />} />
                </Route>

                 <Route path="Submission" element={<SubmissionOutlet/>}>
                 <Route index element={<Submission/>}/>
                  
                  <Route path=":id2" element={<ViewAllAssignmentOutlet/>}>
                  <Route index element ={<ViewAllAssignment/>}/>
                   <Route path=":id3" element={<DisplayAassignment/>}/> 
                  </Route>              
                 </Route>     
              </Route>
            </Route>
          </Routes>
        </Router>
      
  
    </>
  );
}

export default App;
