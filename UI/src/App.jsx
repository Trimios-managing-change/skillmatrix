import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import LandingPage from "./pages/LandingPage";
import { AuthProvider } from "./context/AuthContext";
import ProtectedRoute from "./routes/ProtectedRoute";
import StudentHome from "./pages/StudentHome";
import JobseekerHome from "./pages/JobseekerHome";
import SMEHome from "./pages/SMEHome";
import AcademiaHome from "./pages/AcademiaHome";
import EmployerHome from "./pages/EmployerHome";
import StudentProfile from "./pages/StudentProfile";
import StudentskillAssessment from "./pages/SkillAssessmentPage";
import StudentTrainingPage from "./pages/StudentTrainingPage";

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />

          {/* Protected Student Routes */}
          <Route path="/student-home" element={<ProtectedRoute><StudentHome /></ProtectedRoute>} />
          <Route path="/student-profile" element={<ProtectedRoute><StudentProfile /></ProtectedRoute>} />
          <Route path="/student-skill-assessment" element={<ProtectedRoute><StudentskillAssessment /></ProtectedRoute>} />
          <Route path="/student-training" element={<ProtectedRoute><StudentTrainingPage /></ProtectedRoute>} />

          {/* Other protected roles */}
          <Route path="/jobseeker-home" element={<ProtectedRoute><JobseekerHome /></ProtectedRoute>} />
          <Route path="/sme-home" element={<ProtectedRoute><SMEHome /></ProtectedRoute>} />
          <Route path="/academia-home" element={<ProtectedRoute><AcademiaHome /></ProtectedRoute>} />
          <Route path="/employer-home" element={<ProtectedRoute><EmployerHome /></ProtectedRoute>} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
