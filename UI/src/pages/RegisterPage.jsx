import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../pagescss/RegisterPage.css"; // Adjust the path as necessary
import BASE_URL from "../config.jsx"
 // Adjust the path as necessary
const RegisterPage = () => {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    userType: "", // student, jobseeker, SME, etc.
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log("Form Data:", formData); // Debugging line
      await axios.post(`${BASE_URL}/register`, formData);
      alert("Registered successfully! Please log in.");
      navigate("/login");
    } catch (err) {
      alert("Registration failed: " + err.response?.data?.message);
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form onSubmit={handleSubmit} className="register-form">
        <input type="text" name="fullName" placeholder="Name" onChange={handleChange} required />
        <input type="email" name="email" placeholder="Email" onChange={handleChange} required />
        <input type="password" name="password" placeholder="Password" onChange={handleChange} required />
        <select name="userType" onChange={handleChange} required>
          <option value="">Select Role</option>
<<<<<<< HEAD
          <option value="STUDENT">STUDENT</option>
=======
          <option value="STUDENT">Student</option>
>>>>>>> 246eb68b6dbc3403158800a93374ad4d4cebef66
          <option value="jobseeker">Jobseeker</option>
          <option value="sme">SME</option>
          <option value="academia">Academia</option>
          <option value="employer">Employer</option>
        </select>
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterPage;
