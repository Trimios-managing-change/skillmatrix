import React, { useEffect, useState } from "react";
import axios from "axios";
import StudentHeader from "../components/student/StudentHeader";
import StudentSidebar from "../components/student/StudentSidebar";
import SkillGapAnalysis from "../components/student/SkillGapAnalysis";
import StudentProfileOverlay from "../components/student/StudentProfileOverlay";

import BASE_URL from "../config";
import "../pagescss/StudentHome.css"; // Import the CSS

const StudentHome = () => {
  const token = localStorage.getItem("token"); 
  const [showOverlay, setShowOverlay] = useState(false);

  useEffect(() => {
    const checkProfile = async () => {
      try {
        const res = await axios.get(`${BASE_URL}/api/student/get-user-profile`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (res.status !== 200) {
          setShowOverlay(true);
        }
      } catch (err) {
        setShowOverlay(true); // error → assume profile incomplete
      }
    };

    checkProfile();
  }, [token]);

  return (
    <div className="student-home-container">
      <StudentHeader />
      <div className="student-home-content">
        <StudentSidebar />
        <div className="student-main-area">
          <SkillGapAnalysis />
        </div>
      </div>
      {showOverlay && <StudentProfileOverlay onClose={() => setShowOverlay(false)} />}
    </div>
  );
};

export default StudentHome;
