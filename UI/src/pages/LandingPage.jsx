import React from "react";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";
import "../pagescss/LandingPage.css"; // Import the CSS file for styling

const LandingPage = () => {
  return (
    <>
      <Navbar />
      <section className="hero">
        <div className="hero-content">
          <h1>Verify Skills. Unlock Opportunities.</h1>
          <p>
            Skill Matrix helps students, jobseekers, and professionals verify
            their skills through SMEs and get hired faster.
          </p>
          <div className="cta-buttons">
            <button>Join as Student</button>
            <button>Explore as Employer</button>
          </div>
        </div>
      </section>

      <section className="overview">
        <h2>How Skill Matrix Works</h2>
        <div className="overview-cards">
          <div className="card">👨‍🎓 Students apply for internships</div>
          <div className="card">💼 Jobseekers apply for jobs</div>
          <div className="card">🧑‍🏫 SMEs evaluate and verify skills</div>
          <div className="card">🏢 Employers hire verified candidates</div>
          <div className="card">🏫 Academia tracks and trains their students</div>
        </div>
      </section>

      <Footer />
    </>
  );
};

export default LandingPage;
