import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from '@tailwindcss/vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(),
    tailwindcss(),
  ],
  server: {
    host: true
  },
  test: {
    globals: true,
    environment: "jsdom",
    setupFiles: "./src/setupTests.js", // Setup file for extra configs
  },
})
