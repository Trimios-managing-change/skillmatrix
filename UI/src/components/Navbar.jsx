import React from "react";
import { Link, useNavigate } from "react-router-dom";


const Navbar = () => {
  const navigate = useNavigate();

  const handleLogin = () => {
    navigate("/login");
  };

  const handleSignup = () => {
    navigate("/register");
  };

  return (
    <nav className="navbar">
      <div className="navbar-logo">Skill Matrix</div>
      <ul className="navbar-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/privacy-policy">Privacy Policy</Link></li>
        <li><Link to="/contact-us">Contact Us</Link></li>
        <li><Link to="/about-us">About Us</Link></li>
      </ul>
      <div className="navbar-actions">
        <button className="login-btn" onClick={handleLogin}>Login</button>
        <button className="signup-btn" onClick={handleSignup}>Sign Up</button>
      </div>
    </nav>
  );
};

export default Navbar;

