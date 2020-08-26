import React from 'react';
const SearchContext = React.createContext({});

export default SearchContext;
export const SearchProvider = SearchContext.Provider;