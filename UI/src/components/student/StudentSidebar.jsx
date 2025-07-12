import React from "react";
import { NavLink } from "react-router-dom";
import "../../pagescss/StudentSidebar.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHome,
  faUser,
  faCheckCircle,
  faClipboardCheck,
  faBriefcase,
  faChalkboardTeacher,  // icon for training
  faCog
} from "@fortawesome/free-solid-svg-icons";

const StudentSidebar = () => {
  return (
    <div className="student-sidebar">
      <nav className="sidebar-nav">
        <NavLink to="/student-home" activeClassName="active">
          <FontAwesomeIcon icon={faHome} /> Home
        </NavLink>
        <NavLink to="/student-profile" activeClassName="active">
          <FontAwesomeIcon icon={faUser} /> Profile
        </NavLink>
        <NavLink to="/student-skill-assessment" activeClassName="active">
          <FontAwesomeIcon icon={faCheckCircle} /> Skill Assessment
        </NavLink>
        <NavLink to="/student-skill-verification" activeClassName="active">
          <FontAwesomeIcon icon={faClipboardCheck} /> Skill Verification
        </NavLink>
        <NavLink to="/student-jobs" activeClassName="active">
          <FontAwesomeIcon icon={faBriefcase} /> Jobs
        </NavLink>
        <NavLink to="/student-training" activeClassName="active">
          <FontAwesomeIcon icon={faChalkboardTeacher} /> Training
        </NavLink>
      </nav>
      <div className="sidebar-footer">
        <NavLink to="/student-settings" activeClassName="active">
          <FontAwesomeIcon icon={faCog} /> Settings
        </NavLink>
      </div>
    </div>
  );
};

export default StudentSidebar;
