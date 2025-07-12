import React from "react";


const Footer = () => {
  return (
    <footer className="footer">
      <p>© {new Date().getFullYear()} Skill Matrix. All rights reserved.</p>
      <div className="footer-links">
        <a href="#">Privacy Policy</a>
        <a href="#">Contact</a>
      </div>
    </footer>
  );
};

export default Footer;
