import { render, screen, fireEvent } from "@testing-library/react";
import PopupNote from "../PopupNote";
import * as api from "../../services/api"; 

vi.mock("../../services/api"); 

describe("PopupNote", () => {
  it("renders input fields and buttons", () => {
    render(<PopupNote folderId={1} onClose={() => {}} onCreate={() => {}} />);
    expect(screen.getByPlaceholderText("Note title")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Note content...")).toBeInTheDocument();
    expect(screen.getByText("Create Note")).toBeInTheDocument();
    expect(screen.getByText("Cancel")).toBeInTheDocument();
  });

  it("calls onCreate after submitting with valid input", async () => {
    const mockOnCreate = vi.fn();
    api.createNote.mockResolvedValueOnce({});

    render(<PopupNote folderId={1} onClose={() => {}} onCreate={mockOnCreate} />);

    fireEvent.change(screen.getByPlaceholderText("Note title"), {
      target: { value: "Test Title" },
    });
    fireEvent.change(screen.getByPlaceholderText("Note content..."), {
      target: { value: "Test Content" },
    });

    fireEvent.click(screen.getByText("Create Note"));

    // Allow async/await to resolve
    await new Promise((r) => setTimeout(r, 0));

    expect(api.createNote).toHaveBeenCalledWith(1, {
      title: "Test Title",
      content: "Test Content",
    });
    expect(mockOnCreate).toHaveBeenCalled();
  });
});
