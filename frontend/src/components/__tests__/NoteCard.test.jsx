import { render, screen, fireEvent } from "@testing-library/react";
import NoteCard from "../NoteCard";

describe("NoteCard", () => {
  const sampleNote = { id: 1, title: "Test Note" };

  it("renders note title", () => {
    render(<NoteCard note={sampleNote} onClick={() => {}} />);
    expect(screen.getByText("Test Note")).toBeInTheDocument();
  });

  it("calls onClick when clicked", () => {
    const mockClick = jest.fn();
    render(<NoteCard note={sampleNote} onClick={mockClick} />);
    fireEvent.click(screen.getByText("Test Note"));
    expect(mockClick).toHaveBeenCalledTimes(1);
  });
});
