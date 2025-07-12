import React from "react";
import StudentHeader from "../components/student/StudentHeader";
import StudentSidebar from "../components/student/StudentSidebar";
import StudentProfileContent from "../components/student/StudentProfileContent";
import "../pagescss/StudentPageLayout.css";


const StudentProfile = () => {
  return (
    <div className="student-home-container">
      <StudentHeader />
      <div className="student-home-content">
        <StudentSidebar />
        <div className="student-main-area">
          <StudentProfileContent />
        </div>
      </div>
    </div>
  );
};

export default StudentProfile;
