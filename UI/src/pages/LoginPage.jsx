import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import BASE_URL from "../config";
import { useAuth } from "../context/AuthContext";
import "../pagescss/LoginPage.css";

const LoginPage = () => {
  const [formData, setFormData] = useState({ email: "", password: "" });
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post(`${BASE_URL}/login`, formData);
      const { token, role } = res.data;
      login(token, role);

<<<<<<< HEAD
      // redirect based on role
      switch (role) {
        case "STUDENT":
          navigate("/student-home");
          break;
        case "JOBSEEKER":
          navigate("/jobseeker-home");
          break;
        case "SME":
          navigate("/sme-home");
          break;
        case "ACADEMIA":
          navigate("/academia-home");
          break;
        case "EMPLOYEER":
          navigate("/employer-home");
          break;
        default:
          navigate("/");
      }
=======
      // Delay for state update before navigating
      setTimeout(() => {
        switch (role?.toUpperCase()) {
          case "STUDENT":
            navigate("/student-home");
            break;
          case "JOBSEEKER":
            navigate("/jobseeker-home");
            break;
          case "SME":
            navigate("/sme-home");
            break;
          case "ACADEMIA":
            navigate("/academia-home");
            break;
          case "EMPLOYER":
            navigate("/employer-home");
            break;
          default:
            navigate("/");
        }
      }, 100);
>>>>>>> 246eb68b6dbc3403158800a93374ad4d4cebef66
    } catch (err) {
      alert("Login failed: " + (err.response?.data?.message || "Unknown error"));
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className="login-form">
        <input type="email" name="email" placeholder="Email" onChange={handleChange} required />
        <input type="password" name="password" placeholder="Password" onChange={handleChange} required />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
