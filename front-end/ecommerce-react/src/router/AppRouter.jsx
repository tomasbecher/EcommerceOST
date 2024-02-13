import { Route, Routes, Navigate } from 'react-router-dom';
import { LoginPage } from '../auth';
import { UserProfile } from '../user';
import { useAuthStore } from '../hooks';

export const AppRouter = () => {

  const { status, checkAuthToken} = useAuthStore();

  return (
  <>
    <Routes>
      {
        ( status === 'not-authenticated')  
            ? (
                <>
                    <Route path="/auth/*" element={ <LoginPage /> } />
                    <Route path="/*" element={ <Navigate to="/auth/login" /> } />
                </>
            )
            : (
                <>
                  <Route path="/*" element={ <UserProfile />} />
                  <Route path="/*" element={ <Navigate to="/" /> } />
                </>
            )
    }
    </Routes>
  </>
  )

};