import { useAuthStore } from "../../hooks/useAuthStore"

export const Navbar = () => {

    const { startLogout, user } = useAuthStore();

    return (
      <div className="navbar navbar-dark bg-dark mb-4 px-4">
          <span className="navbar-brand">
              <i className="far fa-user"></i>
              &nbsp;
              &nbsp;
              {localStorage.getItem('username')}
          </span>
  
          <button 
            className="btn btn-outline-danger"
            onClick={startLogout}
          >
              <i className="fas fa-sign-out-alt"></i>
              &nbsp;
              <span>Logout</span>
          </button>
      </div>
    )
  }